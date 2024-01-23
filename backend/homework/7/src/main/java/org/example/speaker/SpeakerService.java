package org.example.speaker;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

    @Bean
    public Speaker speaker1() {
        Speaker speaker1 = new Speaker();
        speaker1.setSpeakerBrand("Sony");
        speaker1.setSpeakerPrice(1000);
        return speaker1;
    }

    @Bean
    public Speaker speaker2() {
        Speaker speaker2 = new Speaker();
        speaker2.setSpeakerPrice(500);
        speaker2.setSpeakerBrand("Bose");
        return speaker2;
    }
}
