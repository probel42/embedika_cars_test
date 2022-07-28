export type Car = {
    id?: String
    regPlate?: String
    brand?: String
    model?: String
    color?: CarColor
    year?: Number
    createTime?: Number
}

export type CarColor = {
    code?: String
    hex?: String
    name?: String
    description?: String
    metallic?: Boolean
}

export type CarInput = {
    regPlate: String
    brand: String
    model: String
    colorCode: Number
    year: Number
}