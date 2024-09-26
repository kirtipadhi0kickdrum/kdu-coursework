package org.example.tyre;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TyreService {

    @Bean
    public Tyre tyre1()
    {
        Tyre tyre1 = new Tyre();
        tyre1.setBrand("Bridgestone");
        tyre1.setPrice(1000);
        return tyre1;
    }

    @Bean
    public Tyre tyre2()
    {
        Tyre tyre2 = new Tyre();
        tyre2.setBrand("MRF");
        tyre2.setPrice(2000);
        return tyre2;
    }



}
