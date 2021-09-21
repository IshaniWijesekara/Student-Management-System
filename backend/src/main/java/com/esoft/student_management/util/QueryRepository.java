package com.esoft.student_management.util;

public interface QueryRepository {

    public final static String GetDailyOrders = "SELECT odr.order_date,orde.oid,orde.qty,orde.total_price,it.item_name from orders odr INNER JOIN order_details orde on odr.oid = orde.oid INNER JOIN item it on orde.iid = it.item_code WHERE odr.order_date = :date";

    public final static String GetDailyProfit ="SELECT SUM(orde.total_price) as total_price,odr.order_date from orders odr INNER JOIN order_details orde on odr.oid = orde.oid INNER JOIN item it on orde.iid = it.item_code WHERE odr.order_date = :date";

    public final static String GetStudentOfDailyBye = "select odr.oid as orderId,s.first_name as fullName,odrde.iid as itemCode from orders odr inner join student s on s.student_id = odr.student_student_id inner join order_details odrde on odr.oid = odrde.oid where odr.order_date = :date";

    public final static String GetAttendanceByStudent = "SELECT * FROM attendance a INNER JOIN student s ON a.student_id = s.student_id WHERE s.student_id = :studentId";

    public final static String GetUser = "SELECT * FROM user a WHERE a.user_name =: name AND a.password =:password";

    public final static String GetAttendnaceByStudentAndClass = "SELECT a.`student_id`,s.`first_name`,s.`nic`,a.`date`,a.`in_time`,a.`month` FROM `attendance` a \n" +
            "INNER JOIN `student` s \n" +
            "WHERE s.`student_id` = a.`student_id`\n" +
            "AND\n" +
            "a.`class_type` =:class_type\n" +
            "AND \n" +
            "a.`date` BETWEEN :fromDate AND :toDate ORDER BY a.`student_id`\n";


    public final static String GetAttendanceByStudentAndDate = "SELECT a.`student_id`,s.`first_name`,s.`nic`,a.`date`,a.`in_time`,a.`month` FROM `attendance` a \n" +
            "INNER JOIN `student` s \n" +
            "WHERE s.`student_id` = a.`student_id`\n" +
            "AND\n" +
            "a.`class_type` =:class_type\n" +
            "AND \n" +
            "s.`student_id` =:student_id\n" +
            "AND\n" +
            "a.`date` BETWEEN :fromDate AND :toDate ORDER BY a.`student_id`\n";

    public final static String GetLateAttendanceByClass = "SELECT a.`student_id`,s.`first_name`,s.`nic`,a.`date`,a.`in_time`,a.`month` FROM `attendance` a \n" +
            "INNER JOIN `student` s \n" +
            "WHERE s.`student_id` = a.`student_id`\n" +
            "AND\n" +
            "a.`class_type` = 1\n" +
            "AND\n" +
            "a.`in_time` >=:in_time\n" +
            "AND a.`date` BETWEEN :fromDate AND :toDate\n";


    public final static String GetEmployeeAttendance = "SELECT u.`id`,u.`name`,u.`email`,u.`address`,e.`date`,e.`in_time`,e.`out_time` FROM `employee_attendance` e\n" +
            "INNER JOIN `user` u\n" +
            "ON u.`id` = e.`user_id` \n" +
            "WHERE e.`date` BETWEEN :fromDate AND :toDate ORDER BY u.`id`";

    public final static String GetPaymentDetails = "SELECT  s.`first_name`,s.`email`,s.`nic`, p.fee, p.`payment_date`,p.`PAYMENT_TYPE` FROM `payment` p\n" +
            "INNER JOIN `student` s \n" +
            "ON s.`student_id` = p.`student_id`\n" +
            "WHERE p.`class_type` =:class_type";

    public final static String GetPaymentProfit = "SELECT SUM(p.`fee`) AS Totfee FROM `payment` p\n" +
            "INNER JOIN `student` s \n" +
            "ON s.`student_id` = p.`student_id`\n" +
            "WHERE p.`class_type` =:class_type";
    public final static String GetUserDetails = "SELECT * FROM USERS WHERE USERNAME=:username";
    public final static String GetUserType = "select ur.name as userType from user u inner join user_role ur on u.code = ur.code where u.user_name =:username";
}
