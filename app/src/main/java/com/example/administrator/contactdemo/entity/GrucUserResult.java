package com.example.administrator.contactdemo.entity;

import com.example.administrator.contactdemo.gruc.ObjectsEntity;

import java.io.Serializable;
import java.util.List;

public class GrucUserResult implements Serializable{

    /**
     * num_results : 165
     * objects : [{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"12344@qq.com","enable":true,"icon_url":null,"id":"SUBUSR081389a8-f7d3-11e5-bbec-fa163e17f4ed","mobile":"0006565","name":"xiaochun2","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"232323@qq.com","enable":true,"icon_url":null,"id":"SUBUSR0dca5760-faf7-11e5-a4e4-fa163e17f4ed","mobile":"232","name":"23323","nickname":"223232","owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"at@qq.com","enable":null,"icon_url":null,"id":"SUBUSR11ca4c52-fc65-11e5-b1a9-fa163e17f4ed","mobile":"865553783585","name":"q6","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"123@qq.com","enable":true,"icon_url":null,"id":"SUBUSR17b82316-fc63-11e5-9e35-fa163e17f4ed","mobile":"6778","name":"hxp ","nickname":"","owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"1833859510@qq.com","enable":null,"icon_url":null,"id":"SUBUSR2d36fbe8-f7b5-11e5-9fe8-fa163e17f4ed","mobile":"18683296846","name":"wenjie2","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"xiangming@globalroam.com","enable":true,"icon_url":null,"id":"SUBUSR3a374132-f746-11e5-8f93-fa163e17f4ed","mobile":"8615008204883","name":"xiangming","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"xiangming1@globalroam.com","enable":true,"icon_url":null,"id":"SUBUSR3a5a712a-f746-11e5-9389-fa163e17f4ed","mobile":"008615008204883","name":"xiangming1","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"guiping@globalroam.com","enable":true,"icon_url":null,"id":"SUBUSR3a993da6-f746-11e5-b1f0-fa163e17f4ed","mobile":"8613982187269","name":"guiping","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"guiping1@globalroam.com","enable":true,"icon_url":null,"id":"SUBUSR3abc9d8c-f746-11e5-8639-fa163e17f4ed","mobile":"008613982187269","name":"guiping1","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}},{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"yongjun@globalroam.com","enable":true,"icon_url":null,"id":"SUBUSR3adef198-f746-11e5-b35c-fa163e17f4ed","mobile":"8618688173429","name":"yongjun","nickname":null,"owner":{"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}}]
     * page : 1
     * total_pages : 17
     */

    private int num_results;
    private int page;
    private int total_pages;
    /**
     * domain_id : DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed
     * email : 12344@qq.com
     * enable : true
     * icon_url : null
     * id : SUBUSR081389a8-f7d3-11e5-bbec-fa163e17f4ed
     * mobile : 0006565
     * name : xiaochun2
     * nickname : null
     * owner : {"developer_id":"DEVELOc2a60578-f73f-11e5-870a-fa163e17f4ed","domain_name":"caas.grcaassip.com","id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed"}
     */

    private List<ObjectsEntity> objects;

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setObjects(List<ObjectsEntity> objects) {
        this.objects = objects;
    }

    public int getNum_results() {
        return num_results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<ObjectsEntity> getObjects() {
        return objects;
    }

}
