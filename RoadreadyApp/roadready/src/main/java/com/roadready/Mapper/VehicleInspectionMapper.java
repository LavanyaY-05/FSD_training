package com.roadready.Mapper;

import com.roadready.dto.InspectionReqDto;
import com.roadready.enums.InspectionType;
import com.roadready.model.VehicleInspection;
import org.springframework.stereotype.Component;

@Component
public class VehicleInspectionMapper {

    public VehicleInspection mapDtoToEntity(InspectionReqDto dto) {
        VehicleInspection inspection = new VehicleInspection();
        inspection.setInspectionType(InspectionType.valueOf(dto.inspectionType()));
        inspection.setCarCondition(dto.condition());
        inspection.setDamageReport(dto.damageReport());
        inspection.setOdometer(dto.odometer());
        inspection.setFuelLevel(dto.fuelLevel());
        inspection.setHasDamage(dto.hasDamage());
        return inspection;
    }

}
