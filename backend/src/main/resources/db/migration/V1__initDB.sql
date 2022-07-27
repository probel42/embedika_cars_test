-- object: public.car | type: TABLE --
-- DROP TABLE IF EXISTS public.car CASCADE;
CREATE TABLE public.car (
                            id uuid NOT NULL,
                            reg_plate varchar(10) NOT NULL,
                            model_id uuid NOT NULL,
                            color_code smallint NOT NULL,
                            year smallint NOT NULL,
                            create_time timestamp NOT NULL,
                            car_model_id uuid,
                            CONSTRAINT car_pk PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON TABLE public.car IS E'Автомобиль';
-- ddl-end --
COMMENT ON COLUMN public.car.id IS E'Идентификатор';
-- ddl-end --
COMMENT ON COLUMN public.car.reg_plate IS E'Номер';
-- ddl-end --
COMMENT ON COLUMN public.car.model_id IS E'Идентификатор модели';
-- ddl-end --
COMMENT ON COLUMN public.car.color_code IS E'Код цвета из справочника';
-- ddl-end --
COMMENT ON COLUMN public.car.year IS E'Год выпуска';
-- ddl-end --
COMMENT ON COLUMN public.car.create_time IS E'Время создания записи';
-- ddl-end --
ALTER TABLE public.car OWNER TO embedika_app;
-- ddl-end --

-- object: public.car_model | type: TABLE --
-- DROP TABLE IF EXISTS public.car_model CASCADE;
CREATE TABLE public.car_model (
                                  id uuid NOT NULL,
                                  brand_id uuid NOT NULL,
                                  name varchar(100) NOT NULL,
                                  car_brand_id uuid,
                                  CONSTRAINT car_model_pk PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON TABLE public.car_model IS E'Модель автомобиля';
-- ddl-end --
COMMENT ON COLUMN public.car_model.id IS E'Идентификатор';
-- ddl-end --
COMMENT ON COLUMN public.car_model.brand_id IS E'Идентификатор бренда';
-- ddl-end --
COMMENT ON COLUMN public.car_model.name IS E'Название модели';
-- ddl-end --
ALTER TABLE public.car_model OWNER TO embedika_app;
-- ddl-end --

-- object: car_model_fk | type: CONSTRAINT --
-- ALTER TABLE public.car DROP CONSTRAINT IF EXISTS car_model_fk CASCADE;
ALTER TABLE public.car ADD CONSTRAINT car_model_fk FOREIGN KEY (car_model_id)
    REFERENCES public.car_model (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.car_brand | type: TABLE --
-- DROP TABLE IF EXISTS public.car_brand CASCADE;
CREATE TABLE public.car_brand (
                                  id uuid NOT NULL,
                                  name varchar(100) NOT NULL,
                                  CONSTRAINT car_brand_pk PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON TABLE public.car_brand IS E'Бренд автомобиля';
-- ddl-end --
COMMENT ON COLUMN public.car_brand.id IS E'Идентификатор';
-- ddl-end --
COMMENT ON COLUMN public.car_brand.name IS E'Название';
-- ddl-end --
ALTER TABLE public.car_brand OWNER TO embedika_app;
-- ddl-end --

-- object: car_brand_fk | type: CONSTRAINT --
-- ALTER TABLE public.car_model DROP CONSTRAINT IF EXISTS car_brand_fk CASCADE;
ALTER TABLE public.car_model ADD CONSTRAINT car_brand_fk FOREIGN KEY (car_brand_id)
    REFERENCES public.car_brand (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --


