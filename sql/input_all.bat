mysql --user superrent --password=superrent < 01_create_db_tables.sql
mysql --user superrent --password=superrent < 02_fill_user_table.sql
mysql --user superrent --password=superrent < 03_fill_branch_table.sql
mysql --user superrent --password=superrent < 04_fill_staff_admin_table.sql
mysql --user superrent --password=superrent < 05_fill_vehicle_class_table.sql

echo finish
pause
