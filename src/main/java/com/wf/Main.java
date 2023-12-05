package com.wf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ${NAME}
 *
 * @author 王飞
 */
public class Main {
    public static void main(String[] args) {


//        CodeHelper.convertFile("1.sql", "sql1.java");
//        CodeHelper.getSqlFile("A.java", "a.sql");
//        test2();
        BigDecimal bigDecimal = new BigDecimal("1.565454");
        System.out.println("Hello world!");
    }


    public static void test2() {
        StringBuffer sql = new StringBuffer();
        List<Object> args = new ArrayList<Object>();
        sql.append(" SELECT ");
        sql.append("	a.province_js_data_id as  provinceDataId ,                                                                                                                          ");
        sql.append("	a.shipping_note_number as  shippingNoteNumber ,                        ");
        sql.append("	a.consignor_name as consignorName,                                                                                                                                               ");
        sql.append("	a.consignee_name as consigneeName,                                                                                                                                               ");
        sql.append("	IF(v.js_vehicle_id is not null,IF(a.driver_name = v.contact_person,a.driver_name,concat( if(v.contact_person is null ,'null',v.contact_person),'（',a.driver_name,'）')),a.actual_carrier_name) as actualCarrierName,                                                                                                                                    ");
        sql.append("	IF(a.actual_carrier_business_license is null,v.road_trans_no,a.actual_carrier_business_license) as actualCarrierBusinessLicense,                                                                                                                                    ");
        sql.append("	a.actual_carrier_id as actualCarrierId                                                                                                                                   ");
        sql.append("	from wccyr.wccy_province_data_js a left JOIN wccyr.wccy_vehicle_js v ON a.vehicle_id = v.vehicle_id                                          ");
        sql.append("	where 1=1                                                                                                                                                  ");
        sql.append("	    and a.province_js_data_id = ?                                                                                                                                  ");
        args.add(1);
        System.out.println(sql);

    }
}