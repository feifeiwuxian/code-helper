select
    a.normal_document_flag as normalDocumentFlag ,
    a.province_js_data_id as provinceDataId ,
    a.original_document_number as originalDocumentNumber ,
    a.shipping_note_number as shippingNoteNumber ,
    a.serial_number as serialNumber ,
    a.vehicle_amount as vehicleAmount ,
    a.transport_typecode as transportTypecode ,
    a.carrier_name as carrierName ,
    a.unified_social_credit_identifier as unifiedSocialCreditIdentifier ,
    a.permit_number as permitNumber ,
    a.consignment_datetime as consignmentDateTime ,
    a.business_typecode as BusinessTypeCode ,
    a.despatch_actual_datetime as DespatchActualDateTime ,
    a.goods_receipt_datetime as GoodsReceiptDateTime ,
    a.consignor_name as consignorName,
    a.consignor_id as consignorId,
    a.place_of_loading_province as placeOfLoadingProvince,
    a.place_of_loading as placeOfLoading,
    a.place_of_loading_city as placeOfLoadingCity,
    a.country_subdivision_code as countrySubdivisionCode ,
    a.consignee_name as consigneeName,
    a.goods_receipt_province as goodsReceiptProvince,
    a.goods_receipt_place as goodsReceiptPlace,
    a.country_subdivision_code2 as countrySubdivisionCode2,
    a.goods_receipt_city as goodsReceiptCity,
    a.total_monetary_amount as totalMonetaryAmount,
    a.remark,
    a.vehicle_id as vehicleId ,
    a.driver_id as driverId ,
    a.driver_name as driverName ,
    a.driving_license as drivingLicense ,
    v.vehicle_plate_color_code as vehiclePlateColorcode ,
    a.vehicle_number as VehicleNumber ,
    a.description_of_goods as descriptionOfGoods,
    a.cargo_type_classification_code as cargoTypeClassificationCode,
    a.goods_item_gross_weight as goodsItemGrossWeight,
    a.created_time as createdTime ,
    a.wccyr_flag as wccyrFlag ,
    a.wccyr_code as wccyrCode ,
    a.wccyr_message as wccyrMessage ,
    a.wccyr_send_date as wccyrSendDate ,
    a.wccyr_send_person_id as wccyrSendPersonId ,
    a.wccyr_send_person_name as wccyrSendPersonName ,
    a.wccyr_modify_date as wccyrModifyDate ,
    a.wccyr_modify_person_id as wccyrModifyPersonId ,
    a.wccyr_modify_person_name as wccyrModifyPersonName ,
    a.insurance_company as insuranceCompany ,
    a.insurance_company_code as insuranceCompanyCode ,
    a.insurance_bill_code as insuranceBillCode ,
    a.insurance_access_address as insuranceAccessAddress ,
    a.actual_mileage as actualMileage ,
    a.goods_entrust_time as goodsEntrustTime ,
    a.contact_name as contactName ,
    a.contact_mobile as contactMobile ,
    a.confirm_goods_weight as confirmGoodsWeight ,
    a.receiver_mobile as receiverMobile ,
    a.register_time as registerTime ,
    a.actual_carrier_id as dataActualCarrierId ,
    a.actual_carrier_name as dataActualCarrierName ,
    a.actual_carrier_business_license as dataActualCarrierBusinessLicense ,
    IF(d.js_driver_id is not null,d.actual_carrier_id,null) as driverActualCarrierId ,
    IF(d.js_driver_id is not null,d.actual_carrier_name,null) as driverActualCarrierName ,
    a.actual_carrier_name as actualCarrierName,
    IF(c.js_carrier_id is not null,c.actual_carrier_business_license,null) as actualCarrierBusinessLicense,
    IF(c.js_carrier_id is not null,c.actual_carrier_id,null) as actualCarrierId,
    IF(c.js_carrier_id is not null,c.actual_carrier_type,null) as actualCarrierType,
    IF(c.js_carrier_id is not null,c.contact_person,null) as carrierContactPerson,
    IF(c.js_carrier_id is not null,c.contact_mobile,null) as carrierContactMobile,
    IF(c.js_carrier_id is not null,c.dependency_code,null) as dependencyCode,
    IF(c.js_carrier_id is not null,c.register_time,null) as carrierRegisterDate,
    IF(c.upload_flag ='3','1',if(c.upload_flag ='2','2','0')) as ifUploadCarrier,
    IF(c.js_carrier_id is not null,d.upload_name,null) as carrierUploadName ,
    IF(c.js_carrier_id is not null,d.upload_time,null) as carrierUploadTime ,
    IF(c.js_carrier_id is not null,d.upload_result,null) as carrierUploadResult ,
    IF( v.upload_flag ='3','1',if(v.upload_flag ='2','2','0')) as ifUploadVehicle,
    IF( d.upload_flag ='3','1',if(d.upload_flag ='2','2','0')) as ifUploadDriver,
    IF( d.js_driver_id is not null,'1','0') as driverIfNull,
    IF( v.js_vehicle_id is not null,'1','0') as vehicleIfNull,
    IF( d.js_driver_id is not null,d.vehicle_class,null) as vehicleClass ,
    IF(d.js_driver_id is not null,d.issuing_organizations,null) as driverIssuingOrganizations ,
    IF(d.js_driver_id is not null,d.valid_period_from,null) as validPeriodFrom ,
    IF(d.js_driver_id is not null,d.valid_period_to,null) as validPeriodTo ,
    IF(d.js_driver_id is not null,d.occup_num,null) as occupNum ,
    IF(d.js_driver_id is not null,d.phone,null) as driverPhone ,
    IF(d.js_driver_id is not null,d.upload_name,null) as driverUploadName ,
    IF(d.js_driver_id is not null,d.upload_time,null) as driverUploadTime ,
    IF(d.js_driver_id is not null,d.upload_result,null) as driverUploadResult ,
    IF(d.sex is not null,d.sex,null) as sex ,
    IF(d.register_time is not null,d.register_time,null) as driverRegisterTime ,
    IF(d.js_driver_id is not null,d.actual_carrier_id,null) as driverActualCarrierId ,
    IF(d.js_driver_id is not null,d.actual_carrier_name,null) as driverActualCarrierName ,
    IF(v.js_vehicle_id is not null,v.vehicle_type_code,null) as vehicleTypeCode ,
    IF(v.js_vehicle_id is not null,v.contact_person,null) as contactPerson ,
    IF(v.js_vehicle_id is not null,v.use_character,null) as useCharacter ,
    IF(v.js_vehicle_id is not null,v.frame_number,null) as frameNumber ,
    IF(v.js_vehicle_id is not null,v.issuing_organizations,null) as vehicleIssuingOrganizations ,
    IF(v.js_vehicle_id is not null,v.register_date,null) as registerDate ,
    IF(v.js_vehicle_id is not null,v.issue_date,null) as issueDate ,
    IF(v.js_vehicle_id is not null,v.vehicle_energy_type_code,null) as vehicleEnergyTypeCode ,
    IF(v.js_vehicle_id is not null,v.total_tonnage,null) as totalTonnage ,
    IF(v.js_vehicle_id is not null,v.road_trans_no,null) as roadTransNo ,
    IF(v.js_vehicle_id is not null,v.hand_car_no,null) as handCarNo ,
    IF(v.js_vehicle_id is not null,v.roadno_invalid_time, null ) as roadnoInvalidTime ,
    IF(v.js_vehicle_id is not null,v.upload_name, null ) as vehicleUploadName ,
    IF(v.js_vehicle_id is not null,v.upload_time,null) as vehicleUploadTime ,
    IF(v.js_vehicle_id is not null,v.upload_result,null) as vehicleUploadResult ,
    IF(v.js_vehicle_id is not null,v.tonnage,null) as tonnage ,
    IF(v.axle_num is not null,v.axle_num,null) as axleNum ,
    IF(v.js_vehicle_id is not null,v.actual_carrier_id,null) as vehicleActualCarrierId ,
    IF(v.js_vehicle_id is not null,v.actual_carrier_name,null) as vehicleActualCarrierName ,
    IF(v.vehicle_license_platecolor is not null,v.vehicle_license_platecolor,null) as vehicleLicensePlatecolor
from (wccyr.wccy_province_data_js a left JOIN wccyr.wccy_vehicle_js v ON(a.vehicle_id = v.vehicle_id))
         LEFT JOIN wccyr.wccy_driver_js d ON( a.driver_id = d.driver_id)
         LEFT JOIN wccyr.wccy_carrier_js c ON( v.actual_carrier_id = c.actual_carrier_id)
where 1=1
  and a.if_delete = '1'
  and a.goods_item_gross_weight > 0
  and a.goods_item_gross_weight <= 99000
  and a.goods_receipt_datetime > a.despatch_actual_datetime
  and a.goods_receipt_datetime < now()
  and a.despatch_actual_datetime < now()
  and a.total_monetary_amount >= 100
  and a.total_monetary_amount <= 10000
  and a.created_time >= ?
  and a.created_time <= ?
  and a.despatch_actual_datetime >= ?
  and a.despatch_actual_datetime <= ?
  and a.goods_receipt_datetime >= ?
  and a.goods_receipt_datetime <= ?
  and a.wccyr_send_date >= ?
  and a.wccyr_send_date <= ?
  and v.upload_time >= ?
  and v.upload_time <= ?
  and d.upload_time >= ?
  and d.upload_time <= ?
  and a.wccyr_flag = ?
  and a.province_js_data_id = ?
  and a.carrier_name like concat('%', ?, '%')
  and a.shipping_note_number like concat('%', ?, '%')
  and a.original_document_number like concat('%', ?, '%')
  and a.wccyr_send_person_name like concat('%', ?, '%')
  and a.consignor_name like concat('%', ?, '%')
  and d.upload_flag = '3'
  and d.upload_flag = '2'
  and d.perfect_flag = 0
  and( d.upload_flag = '1' or d.upload_flag is null)
  and v.upload_flag = '3'
  and v.upload_flag = '2'
  and v.perfect_flag = 0
  and( v.upload_flag = '1' or v.upload_flag is null)
  and d.upload_name like concat('%', ?, '%')
  and v.upload_name like concat('%', ?, '%')
  and a.vehicle_id = ?
  and a.driver_id = ?
  and a.normal_document_flag = ?
  and a.goods_item_gross_weight <= 36000
  and a.goods_item_gross_weight > 36000
  and c.upload_flag = '3'
  and c.upload_flag = '2'
  and c.perfect_flag = 0
  and( c.upload_flag = '1' or c.upload_flag is null)
order by a.province_js_data_id desc
