package com.dai.xmp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplicationcom {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationcom.class, args);
    }
}
private void johnsUnusedCodeSmellMethod() {
       String buggy=null;
        buggy.trim();
        
        System.out.println("Foo!!!!!?");
}
