mysql --user superrent --password=superrent < 0_0_drop_db_tables.sql
mysql --user superrent --password=superrent < 0_1_create_db_tables.sql
mysql --user superrent --password=superrent < 1_1_fill_branch_table.sql
mysql --user superrent --password=superrent < 1_1_fill_vehicle_class_table.sql
mysql --user superrent --password=superrent < 1_2_fill_equipment_type_table.sql
mysql --user superrent --password=superrent < 1_3_fill_support_table.sql
mysql --user superrent --password=superrent < 1_4_fill_insurance_table.sql
mysql --user superrent --password=superrent < 2_1_fill_vehicle_table.sql
mysql --user superrent --password=superrent < 2_2_fill_equipment_table.sql
mysql --user superrent --password=superrent < 2_3_fill_user_table.sql
mysql --user superrent --password=superrent < 2_4_fill_staff_table.sql
mysql --user superrent --password=superrent < 3_1_fill_user_table.sql
mysql --user superrent --password=superrent < 3_2_fill_staff_table.sql
mysql --user superrent --password=superrent < 3_3_fill_customer_table.sql
mysql --user superrent --password=superrent < 3_4_fill_reservation_info_table.sql
mysql --user superrent --password=superrent < 3_5_fill_reserve_equipment_table.sql
mysql --user superrent --password=superrent < 3_6_fill_buy_insurance_table.sql
mysql --user superrent --password=superrent < 3_7_fill_creditcard_table.sql
mysql --user superrent --password=superrent < 3_8_fill_rent_table.sql
mysql --user superrent --password=superrent < 3_9_fill_payment_table.sql
mysql --user superrent --password=superrent < 3_10_fill_item_table.sql
mysql --user superrent --password=superrent < 3_11_fill_return_record_table.sql





echo finish
pause