import {gql} from 'apollo-boost'

export const GET_CARS_NUMBER = gql`
    query GetCarsNumber {
        carsNumber
    }
`

export const GET_CARS = gql`
    query GetCars($page: Int!, $size: Int!) {
        cars(page: $page, size: $size) {
            regPlate
            brand
            model
            color {
                hex
                name
                description
            }
            year
            createTime
        }
    }
`

export const GET_CAR = gql`
    query GetCar($id: ID!) {
        car(id: $id) {
            id
            regPlate
            brand
            model
            color {
                code
                hex
                name
                description
                metallic
            }
            year
            createTime
        }
    }
`

export const GET_CAR_BRANDS = gql`
    query GetCarBrands {
        carBrands {
            name
        }
    }
`

export const GET_CAR_BRAND = gql`
    query GetCarBrand($name: String!) {
        carBrand(name: $name) {
            name
            models {
                name
            }
        }
    }
`

export const GET_CAR_COLORS = gql`
    query GetCarColors {
        carColors {
            code
            hex
            name
            description
            metallic
        }
    }
`

export const ADD_CAR = gql`
    mutation AddCar($car: CarInput!) {
        addCar(car: $car)
    }
`

export const REMOVE_CAR = gql`
    mutation RemoveCar($id: ID!) {
        removeCar(id: $id)
    }
`
