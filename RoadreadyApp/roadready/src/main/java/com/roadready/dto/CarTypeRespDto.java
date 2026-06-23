package com.roadready.dto;

import com.roadready.enums.CarTransmission;
import com.roadready.enums.CarType;
import com.roadready.enums.FuelType;

import java.util.List;

public record CarTypeRespDto (
        List<CarType> carTypes,
        List<CarTransmission> carTransmissions,
        List<FuelType> fuelTypes){
}
