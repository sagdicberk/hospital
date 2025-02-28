package com.example.application.util.modelmapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelMapperServiceImp implements ModelMapperService {
    private ModelMapper modelMapper;


    @Override
    public ModelMapper forResponse() {

        //Gevşek bağlı bir mapper
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }

    @Override
    public ModelMapper forRequest() {

        //Normal bağlı bir mapper
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

}
