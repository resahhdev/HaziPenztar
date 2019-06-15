/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  resahh
 * Created: 2019.05.20.
 */

create table categories( id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         category VARCHAR(20))

create table directions( id INTEGER PRIMARY KEY NOT NULL,
                         direction char(2) )

create table wallet( id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     date VARCHAR(10),
                     price VARCHAR(10),
                     comment VARCHAR(50),
                     categoryId INTEGER,
                     directionId INTEGER )

create table users( id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    username VARCHAR(50),
                    password VARCHAR(250))



insert into directions( id, direction ) values ( 1, 'be'), ( 2, 'ki' )

insert into categories( category ) values
                        ( 'Fizetés' ),( 'Háztartás' ),( 'Rezsi' ),
                        ( 'Ajándék' ),( 'Állat' ),( 'Magán' ),
                        ( 'Élelmiszer' ),( 'Auto' ),( 'Ruha' )

insert into users( username, password ) values ( 'admin', 'admin' )