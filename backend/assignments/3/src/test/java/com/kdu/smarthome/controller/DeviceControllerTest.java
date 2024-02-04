package com.kdu.smarthome.controller;

import com.kdu.smarthome.utility.TestSuiteDataManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static com.kdu.smarthome.utility.RequestBuilders.buildAddDeviceRequest;
import static com.kdu.smarthome.utility.RequestBuilders.buildDeviceRegistrationRequest;

public class DeviceControllerTest {

    /**
     * Test device registration with valid request data.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void deviceRegisterWithValidRequestData(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String deviceName = (args.length > 1) ? args[1].trim() : "device1";
            String devicePassword = (args.length > 2) ? args[2].trim() : "pwd";
            String username = (args.length > 3) ? args[3].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/register")
                            .content(buildDeviceRegistrationRequest(kickstoneId, deviceName, devicePassword))
                            // Add the Authorization header with the user token
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(result -> {
                        // Fetch existing data
                        Map<String, Object> existingData = (Map<String, Object>) TestSuiteDataManager.readData("registeredDevices");
                        // Check if there is any existing data
                        if (existingData == null) {
                            existingData = new HashMap<>();
                        }

                        // Add new user data to the existing data
                        existingData.put(kickstoneId, Map.of(
                                "kickstoneId", kickstoneId,
                                "devicePassword", devicePassword,
                                "user", username
                        ));

                        // Write user data and token to the test suite data manager
                        TestSuiteDataManager.writeData("registeredDevices", existingData);

                        System.out.println("deviceRegisterWithValidRequestData TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("deviceRegisterWithValidRequestData TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

    /**
     * Test device registration with invalid credentials.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void deviceRegisterWithInvalidCredentials(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String deviceName = (args.length > 1) ? args[1].trim() : "device1";
            String devicePassword = (args.length > 2) ? args[2].trim() : "invalidPassword";
            String username = (args.length > 3) ? args[3].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/register")
                            .content(buildDeviceRegistrationRequest(kickstoneId, deviceName, devicePassword))
                            // Add the Authorization header with the user token
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                    .andDo(result -> {
                        System.out.println("deviceRegisterWithInvalidCredentials TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("deviceRegisterWithInvalidCredentials TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

    /**
     * Test unavailable device registration.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void registerUnavailableDevice(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String deviceName = (args.length > 1) ? args[1].trim() : "device2";
            String devicePassword = (args.length > 2) ? args[2].trim() : "pwd";
            String username = (args.length > 3) ? args[3].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/register")
                            .content(buildDeviceRegistrationRequest(kickstoneId, deviceName, devicePassword))
                            // Add the Authorization header with the user token
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                    .andDo(result -> {
                        System.out.println("registerUnavailableDevice TEST PASSED");
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("registerUnavailableDevice TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

    /**
     * Test device registration with invalid credentials.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void deviceRegisterByNonAdmin(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String deviceName = (args.length > 1) ? args[1].trim() : "device1";
            String devicePassword = (args.length > 2) ? args[2].trim() : "invalidPassword";
            String username = (args.length > 3) ? args[3].trim() : "user2";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/register")
                            .content(buildDeviceRegistrationRequest(kickstoneId, deviceName, devicePassword))
                            // Add the Authorization header with the user token
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                    .andDo(result -> {
                        System.out.println("deviceRegisterByNonAdmin TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("deviceRegisterByNonAdmin TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

    /**
     * Test adding device to house with valid request data.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void addDeviceWithValidRequestData(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String username = (args.length > 1) ? args[1].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            // Read house id from testSuiteData
            Map<String, Object> registeredHousesMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredHouses");
            if (registeredHousesMap == null) {
                registeredHousesMap = new HashMap<>();
            }

            // Assuming your house data structure looks like this
            Map<String, Object> houseData = new HashMap<>();
            houseData.put("id", "validHouseId"); // Replace with the actual valid house ID
            houseData.put("rooms", Collections.emptyList()); // Add necessary data

            registeredHousesMap.put(username, houseData);

            // Assuming your room data structure looks like this
            Map<String, Object> roomData = new HashMap<>();
            roomData.put("id", "validRoomId"); // Replace with the actual valid room ID
            List<Map<String, Object>> rooms = new ArrayList<>();
            rooms.add(roomData);
            houseData.put("rooms", rooms);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/add")
                            .content(buildAddDeviceRequest("validHouseId", "validRoomId", kickstoneId))  // Replace with valid houseId and roomId
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(result -> {
                        // Fetch existing data
                        Map<String, Object> existingData = (Map<String, Object>) TestSuiteDataManager.readData("registeredDevices");

                        // Check if there is any existing data
                        if (existingData == null) {
                            existingData = new HashMap<>();
                        }

                        // Fetch device details
                        Map<String, Object> deviceData = (Map<String, Object>) existingData.get(kickstoneId);

                        // Convert deviceData to a mutable map
                        deviceData = new HashMap<>(deviceData);

                        // Add new user data to the existing data
                        deviceData.put("houseId", "validHouseId"); // Replace with the actual valid house ID
                        deviceData.put("roomId", "validRoomId"); // Replace with the actual valid room ID

                        // Update existingData with the modified deviceData
                        existingData.put(kickstoneId, deviceData);

                        // Write user data and token to the test suite data manager
                        TestSuiteDataManager.writeData("registeredDevices", existingData);

                        System.out.println("addDeviceWithValidRequestData TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("addDeviceWithValidRequestData TEST FAILED " + ex.getLocalizedMessage()));
        }
    }


    /**
     * Test adding device to invalid house.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */


    public static void addDeviceToInvalidHouse(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String username = (args.length > 1) ? args[1].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            // Read house id from testSuiteData
            Map<String, Object> registeredHousesMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredHouses");
            if (registeredHousesMap == null) {
                registeredHousesMap = new HashMap<>();
            }

            // Assuming your house data structure looks like this
            Map<String, Object> houseData = new HashMap<>();
            houseData.put("id", "invalidHouseId"); // Change to the actual invalid house ID
            houseData.put("rooms", Collections.emptyList()); // Add necessary data

            registeredHousesMap.put(username, houseData);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/add")
                            .content(buildAddDeviceRequest("invalidHouseId", "roomId", kickstoneId))  // Replace with valid roomId
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                    .andDo(result -> {
                        System.out.println("addDeviceToInvalidHouse TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("addDeviceToInvalidHouse TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

    /**
     * Test adding device to invalid house.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void addDeviceToInvalidRoom(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "111";
            String username = (args.length > 1) ? args[1].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            // Read house id from testSuiteData
            Map<String, Object> registeredHousesMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredHouses");
            if (registeredHousesMap == null) {
                registeredHousesMap = new HashMap<>();
            }

            // Assuming your house data structure looks like this
            Map<String, Object> houseData = new HashMap<>();
            houseData.put("id", "invalidHouseId"); // Change to the actual invalid house ID
            houseData.put("rooms", Collections.emptyList()); // Add necessary data

            registeredHousesMap.put(username, houseData);

            // Assuming your room data structure looks like this
            Map<String, Object> roomData = new HashMap<>();
            roomData.put("id", "invalidRoomId"); // Change to the actual invalid room ID
            List<Map<String, Object>> rooms = new ArrayList<>();
            rooms.add(roomData);
            houseData.put("rooms", rooms);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/add")
                            .content(buildAddDeviceRequest("invalidHouseId", "invalidRoomId", kickstoneId))  // Replace with valid houseId and roomId
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                    .andDo(result -> {
                        System.out.println("addDeviceToInvalidRoom TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("addDeviceToInvalidRoom TEST FAILED " + ex.getLocalizedMessage()));
        }
    }


    /**
     * Test adding unregistered device to house.
     * Device is present in the inventory
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void addUnregisteredDevice(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "222";
            String username = (args.length > 1) ? args[1].trim() : "user1";

            //Add device2 to inventory but it will stay unregistered by the user
            InventoryControllerTest.addDeviceToInventory(mockMvc,kickstoneId,"device2","pwd2", username);

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            // Read house id from testSuiteData
            Map<String, Object> registeredHousesMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredHouses");
            Map<String, Object> houseData = (Map<String, Object>) registeredHousesMap.get(username);
            String houseId = houseData.get("id").toString();

            // Read room id from testSuiteData
            List<Map<String, Object>> rooms = (List<Map<String, Object>>) houseData.get("rooms");
            String roomId = (String) (rooms.get(0)).get("id");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/add")
                            .content(buildAddDeviceRequest(houseId, roomId, kickstoneId))
                            // Add the Authorization header with the user token
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(result -> {
                        System.out.println("addUnregisteredDevice TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("addUnregisteredDevice TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

    /**
     * Test adding unavailable device to house.
     *
     * @param mockMvc The MockMvc instance.
     * @param args    Optional comma-separated values containing device registration details:
     *                If values are not provided, default values will be used.
     */
    public static void addUnavailableDevice(MockMvc mockMvc, String... args) {
        try {
            String kickstoneId = (args.length > 0) ? args[0].trim() : "333";
            String username = (args.length > 1) ? args[1].trim() : "user1";

            // Read user token from testSuiteData
            Map<String, Object> registeredUsersMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredUsers");
            Map<String, Object> userData = (Map<String, Object>) registeredUsersMap.get(username);
            String userToken = (String) userData.get("token");

            // Read house id from testSuiteData
            Map<String, Object> registeredHousesMap = (Map<String, Object>) TestSuiteDataManager.readData("registeredHouses");
            Map<String, Object> houseData = (Map<String, Object>) registeredHousesMap.get(username);
            String houseId = houseData.get("id").toString();

            // Read room id from testSuiteData
            List<Map<String, Object>> rooms = (List<Map<String, Object>>) houseData.get("rooms");
            String roomId = (String) (rooms.get(0)).get("id");

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/device/add")
                            .content(buildAddDeviceRequest(houseId, roomId, kickstoneId))
                            // Add the Authorization header with the user token
                            .header("Authorization", "Bearer " + userToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(result -> {
                        System.out.println("addUnavailableDevice TEST PASSED");
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(("addUnavailableDevice TEST FAILED " + ex.getLocalizedMessage()));
        }
    }

}
