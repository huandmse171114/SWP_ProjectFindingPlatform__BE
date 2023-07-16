package com.findhub.finhubbackend;

import java.sql.Date;

import com.findhub.finhubbackend.controller.ApiController;
import com.findhub.finhubbackend.controller.MemberController;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.application.ApplicationStatus;
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.util.Utils;

public class alo {

    public static void main(String[] args) {
        // Date d = new Date(System.currentTimeMillis());
        // Date d2 = new Date(d.getTime() + 1000 * 60 * 60 * 24 * 5);
        // System.out.println(d);
        // System.out.println(d2);

        // System.out.println(ProjectStatus.nameOf(1));
        // GenericClass<String>
        // String x = "1";
        // GenericClass<String> a = new GenericClass<>();

        // int z = GenericClass.cast("1");
        // System.out.println(a.cast(1));

        // System.out.println(ApplicationStatus.isExisted(45));
        // System.out.println(ApplicationStatus.isExisted(0));
        // System.out.println(ApplicationStatus.nameOf(45));
        // System.out.println(ApplicationStatus.nameOf(0));
        // System.out.println(Utils.capitalize(null));
        // System.out.println(Utils.capitalize(""));
        // System.out.println(Utils.capitalize("1"));
        // System.out.println(Utils.addDate(new Date(System.currentTimeMillis()), -10));

        // Date d = Date.valueOf("2020-06-10");
        // System.out.println(d.toString());

        // System.out.println(ProjectStatus.isExisted("active"));
        // System.out.println(ProjectStatus.isExisted("active1"));
        // // System.out.println(ProjectStatus.valueOf(null));
        // System.out.println(ProjectStatus.valueOf("active".toUpperCase()).getClass());
        System.out.println(AccountRole.ADMIN.toString());

        // System.out.println( mc.updateStatus(7, 100));
    }
}
