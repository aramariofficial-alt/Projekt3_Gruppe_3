DROP DATABASE IF EXISTS projekt3_gruppe_3_db;

CREATE DATABASE projekt3_gruppe_3_db;


USE projekt3_gruppe_3_db;


CREATE TABLE car (
                     vehicle_number INT AUTO_INCREMENT PRIMARY KEY,
                     chassis_number VARCHAR(17) NOT NULL UNIQUE,
                     brand VARCHAR(50) NOT NULL,
                     model VARCHAR(50) NOT NULL,
                     color VARCHAR(50) NOT NULL,

                     status ENUM(
                     'AVAILABLE',
                     'RENTED',
                     'RETURNED',
                     'DAMAGED',
                     'READY_FOR_TRANSPORT'
                     )NOT NULL


);


CREATE TABLE customer (
                          customer_id INT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          phone VARCHAR(20),
                          address VARCHAR(100)


);
CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      name VARCHAR(100) NOT NULL,

                      role ENUM(
                     'DATA_REGISTRATION',
                     'DAMAGE_AND_REPAIR',
                     'BUSINESS_DEVELOPER'
                     ) NOT NULL



);
CREATE TABLE rental_agreement (
                                  agreement_id INT AUTO_INCREMENT PRIMARY KEY,
                                  vehicle_number int NOT NULL,
                                  customer_id int NOT NULL,
                                  created_by int NOT NULL,
                                  pickup_location VARCHAR(100) NOT NULL,
                                  start_date DATE NOT NULL,
                                  end_date DATE NOT NULL,
                                  monthly_price DECIMAL (10, 2) NOT NULL,

                                  subscription_type ENUM(
                     'LIMITED',
                     'UNLIMITED'
                     ) NOT NULL,

                                  FOREIGN KEY (vehicle_number)
                                      REFERENCES car(vehicle_number),

                                  FOREIGN KEY (customer_id)
                                      REFERENCES customer(customer_id),

                                  FOREIGN KEY (created_by)
                                      REFERENCES user(user_id)



);
CREATE TABLE damage_report (
                               report_id INT AUTO_INCREMENT PRIMARY KEY,
                               agreement_id INT NOT NULL UNIQUE,
                               created_by INT NOT NULL,
                               report_date DATE NOT NULL,

                               FOREIGN KEY (agreement_id)
                                   REFERENCES rental_agreement (agreement_id),

                               FOREIGN KEY (created_by)
                                   REFERENCES user (user_id)



);
CREATE TABLE damage (
                        damage_id INT AUTO_INCREMENT PRIMARY KEY,
                        report_id INT NOT NULL,
                        description VARCHAR(255) NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,

                        FOREIGN KEY (report_id)
                            REFERENCES damage_report (report_id)
                    );
