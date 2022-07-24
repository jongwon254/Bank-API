CREATE ROLE bank_dev_rw WITH LOGIN PASSWORD 'dev_bank_passwd';

GRANT ALL PRIVILEGES ON DATABASE bank_db TO bank_dev_rw;
