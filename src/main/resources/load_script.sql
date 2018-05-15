INSERT INTO STATUS (name) (SELECT 'dead' FROM STATUS WHERE name = 'dead' HAVING count(*)=0)
INSERT INTO STATUS (name) (SELECT 'alive' FROM STATUS WHERE name = 'alive' HAVING count(*)=0)