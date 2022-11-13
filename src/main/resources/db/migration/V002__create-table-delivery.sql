create table delivery (
	id bigint not null auto_increment,
    customer_id bigint not null,
    tax decimal(10, 2) not null,
    status varchar(20) not null,
    order_date datetime not null,
    completion_date datetime,
    
    recipient_name varchar(60) not null,
    recipient_address varchar(255) not null,
    recipient_street_number varchar(30) not null,
    recipient_address_complement varchar(60),
    recipient_district varchar(30) not null,
    
    primary key (id),
    foreign key (customer_id) references customer(id)
);