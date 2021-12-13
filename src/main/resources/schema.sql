CREATE TABLE IF NOT EXISTS user_details (
    username text,
    roles text,
    enabled boolean,
    password text,
    PRIMARY KEY (username, roles)
);

CREATE TABLE IF NOT EXISTS client_details (
    client_id text,
    resource_id text,
    grant_type text,
    scope text,
    secret text,
    PRIMARY KEY (client_id, resource_id, grant_type)
);

--INSERT INTO user_details (username, roles, enabled, password) VALUES();