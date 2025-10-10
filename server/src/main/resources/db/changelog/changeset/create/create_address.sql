CREATE TABLE addresses
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    region_id   BIGINT NOT NULL,
    district_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    created_by UUID NOT NULL ,
    updated_by UUID NOT NULL ,
    details     VARCHAR(500),
    CONSTRAINT fk_address_region FOREIGN KEY (region_id) REFERENCES regions (id),
    CONSTRAINT fk_address_district FOREIGN KEY (district_id) REFERENCES districts (id)
);