package com.accton.newframework.core.domain.undefine.fr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.accton.newframework.core.domain.undefine.fr.model.FrFrontEnd;
import com.accton.newframework.core.domain.undefine.fr.model.FrResponse;
import org.springframework.stereotype.Component;

@Component
public class FrServiceImpl implements FrService {

    
    /**
     * Get response results
     * @param type Type of data obtained
     * @return Results for frontend group UI
     */
    @Override
    public FrResponse getResponse(String type) throws Exception {
        FrResponse frResponse = new FrResponse();
        if (type.equals("default")) {
            
            List<FrFrontEnd> get_data_list = new ArrayList<>();
            FrFrontEnd tmp_data = new FrFrontEnd();
            tmp_data.setId("1047");
            tmp_data.setName("Number");
            tmp_data.setType("Text");
            tmp_data.setValue("PNA201200064");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1030");
            tmp_data.setName("Status");
            tmp_data.setType("inputbox");
            tmp_data.setValue("100.Complete");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1052");
            tmp_data.setName("Description of Change");
            tmp_data.setType("inputbox");
            tmp_data.setValue("新增规格书");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1053");
            tmp_data.setName("Reason For Change");
            tmp_data.setType("inputbox");
            tmp_data.setValue("");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1049");
            tmp_data.setName("Reason Code");
            tmp_data.setType("inputbox");
            tmp_data.setValue("新增规格书");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("3742");
            tmp_data.setName("Workflow");
            tmp_data.setType("inputbox");
            tmp_data.setValue("Spec Update Workflow");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1099");
            tmp_data.setName("Change Analyst");
            tmp_data.setType("inputbox");
            tmp_data.setValue("");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1050");
            tmp_data.setName("Originator");
            tmp_data.setType("inputbox");
            tmp_data.setValue("AT20200003 刘力玮 (liwei_liu)");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1061");
            tmp_data.setName("Date Originated");
            tmp_data.setType("Date");
            tmp_data.setValue("2020/12/22");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1051");
            tmp_data.setName("Date Released");
            tmp_data.setType("Date");
            tmp_data.setValue("2020/12/23");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("3743");
            tmp_data.setName("Final Complete Date");
            tmp_data.setType("Date");
            tmp_data.setValue("2020/12/23");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1003");
            tmp_data.setName("Product Line(s)");
            tmp_data.setType("inputbox");
            tmp_data.setValue("");
            get_data_list.add(tmp_data);
            
            frResponse.setPosts(get_data_list);
            frResponse.setFr_status(true);

        } 
        else if (type.equals("get")) {
            
            List<FrFrontEnd> get_data_list = new ArrayList<>();
            FrFrontEnd tmp_data = new FrFrontEnd();
            tmp_data.setId("1047");
            tmp_data.setName("Number");
            tmp_data.setType("Text");
            tmp_data.setValue("PNA201200064");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1030");
            tmp_data.setName("Status");
            tmp_data.setType("inputbox");
            tmp_data.setValue("100.Complete");
            get_data_list.add(tmp_data);
            
            frResponse.setPosts(get_data_list);
            frResponse.setFr_status(true);

        } 
        else if (type.equals("300")) {
            
            List<FrFrontEnd> get_data_list = new ArrayList<>();
            FrFrontEnd tmp_data = new FrFrontEnd();
            for(int i = 1; i <= 300; i++) {
                if (i % 2 == 0) {
                    tmp_data = new FrFrontEnd();
                    tmp_data.setId(String.valueOf(i));
                    tmp_data.setName("Number_" + String.valueOf(i));
                    tmp_data.setType("Text");
                    tmp_data.setValue("PNA201200064");
                    get_data_list.add(tmp_data);
                }
                else {
                    tmp_data = new FrFrontEnd();
                    tmp_data.setId(String.valueOf(i));
                    tmp_data.setName("Status_" + String.valueOf(i));
                    tmp_data.setType("inputbox");
                    tmp_data.setValue("100.Complete");
                    get_data_list.add(tmp_data);
                }
            }
            
            frResponse.setPosts(get_data_list);
            frResponse.setFr_status(true);
        }
        else {
            List<FrFrontEnd> get_data_list = new ArrayList<>();
            FrFrontEnd tmp_data = new FrFrontEnd();
            tmp_data.setId("1047");
            tmp_data.setName("Number");
            tmp_data.setType("Text");
            tmp_data.setValue("");
            get_data_list.add(tmp_data);
            
            tmp_data = new FrFrontEnd();
            tmp_data.setId("1030");
            tmp_data.setName("Status");
            tmp_data.setType("inputbox");
            tmp_data.setValue("");
            get_data_list.add(tmp_data);
            
            frResponse.setPosts(get_data_list);
            frResponse.setFr_status(false);
        }

        return frResponse;
    }

    /**
     * Save value
     * @param dataList Data that needs to be stored
     * @return Results for frontend group UI
     */
    @Override
    public FrResponse setSaveValue(Map<String, Object> dataList) throws Exception {
        List<FrFrontEnd> get_data_list = new ArrayList<>();

        String status = String.valueOf(dataList.get("1030"));

        FrFrontEnd tmp_data = new FrFrontEnd();
        tmp_data.setId("status");
        tmp_data.setName("Status");
        tmp_data.setType("inputbox");
        tmp_data.setValue(status);
        
        get_data_list.addAll(getResponse("default").getPosts());
                
        FrResponse frResponse = new FrResponse();
        frResponse.setPosts(get_data_list);
        if (status.equals("100.Complete123")) {
            frResponse.setFr_status(true);
        } else {
            frResponse.setFr_status(false);
        }
        
        return frResponse;
    }
    
}
