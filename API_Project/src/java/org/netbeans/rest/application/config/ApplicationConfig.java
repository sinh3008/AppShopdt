/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author This PC
 */
@javax.ws.rs.ApplicationPath("project")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(API.AccountAPI.class);
        resources.add(API.AttributeAPI.class);
        resources.add(API.AttributeValuesAPI.class);
        resources.add(API.BilldetailsService.class);
        resources.add(API.BillsService.class);
        resources.add(API.CategoryAPI.class);
        resources.add(API.CustomerService.class);
        resources.add(API.PaymentAPI.class);
        resources.add(API.ProductAPI.class);
        resources.add(API.ShippingAPI.class);
        resources.add(API.UserAPI.class);
    }
    
}
