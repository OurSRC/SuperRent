mysql --user superrent --password=superrent < 00_drop_db_tables.sql
mysql --user superrent --password=superrent < 01_create_db_tables.sql
mysql --user superrent --password=superrent < 02_fill_user_table.sql
mysql --user superrent --password=superrent < 03_fill_branch_table.sql
mysql --user superrent --password=superrent < 04_fill_staff_admin_table.sql
mysql --user superrent --password=superrent < 05_fill_vehicle_class_table.sql
mysql --user superrent --password=superrent < 06_fill_customer.sql
mysql --user superrent --password=superrent < 07_fill_vehicle_table.sql
mysql --user superrent --password=superrent < 08_fill_equipment_type.sql
mysql --user superrent --password=superrent < 09_fill_equipment.sql
mysql --user superrent --password=superrent < 10_fill_insurance.sql
mysql --user superrent --password=superrent < 11_fill_creditcard.sql
mysql --user superrent --password=superrent < 12_fill_payment.sql
mysql --user superrent --password=superrent < 14_fill_item.sql
mysql --user superrent --password=superrent < 15_fill_reservation_info.sql
mysql --user superrent --password=superrent < 16_fill_reserve_equipment.sql
mysql --user superrent --password=superrent < 17_fill_rent.sql
mysql --user superrent --password=superrent < 18_fill_return_record.sql
mysql --user superrent --password=superrent < 19_fill_support.sql
mysql --user superrent --password=superrent < 20_fill_buy_insurance.sql





echo finish
pause
