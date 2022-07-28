import type {Car, CarColor} from './Types';
import * as Queries from './Queries';
import {query, ReadableQuery} from 'svelte-apollo';

export default class DataLoader {

    public static getCarsNumber(): Promise<{ carsNumber: number }> {
        return this.createPromise(query(Queries.GET_CARS_NUMBER));
    }

    public static getCars(pageNumber: number, perPage: number): Promise<{ cars: Car[] }> {
        const opts = {variables: {page: pageNumber - 1, size: perPage}};
        return this.createPromise(query(Queries.GET_CARS, opts));
    }

    public static loadCar(id: String): Promise<{ car: Car }> {
        const opts = {variables: {id: id}};
        return this.createPromise(query(Queries.GET_CAR, opts));
    }

    public static getCarBrands(): Promise<{ carBrands: { name: string }[] }> {
        return this.createPromise(query(Queries.GET_CAR_BRANDS));
    }

    public static getCarBrand(name: string): Promise<{ carBrand: { name: string, models: { name: string }[] } }> {
        const opts = {variables: {name: name}};
        return this.createPromise(query(Queries.GET_CAR_BRAND, opts));
    }

    public static getCarColors(): Promise<{ carColors: CarColor[] }> {
        return this.createPromise(query(Queries.GET_CAR_COLORS));
    }

    private static createPromise<T>(query: ReadableQuery<T>): Promise<T> {
        return new Promise<T>((resolve, reject) => {
            query.subscribe(result => {
                if (!result.loading) {
                    if (result.data)
                        resolve(result.data);
                    else if (result.error)
                        reject(result.error);
                }
            });
        });
    }
}
