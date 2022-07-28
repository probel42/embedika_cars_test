<script lang="ts">
    import type {Car} from '../data/Types';

    export let car: Car;
</script>

<style>
    tr {
        cursor: pointer;
    }

    tr td {
        border: 1px solid #5a5a5a;
        border-collapse: collapse;
        padding-left: 5px;
        padding-right: 5px;
    }

    tr:nth-child(odd) td {
        background-color: #D2E1FD;
    }

    tr:nth-child(even) td {
        background-color: #E8EFFF;
    }

    tr:hover td {
        background-color: #E0D3E3;
    }
</style>

<tr>
    <td>{car.regPlate}</td>
    <td>{car.brand}</td>
    <td>{car.model}</td>
    <td style="background-color: {car.color.hex}" title="{car.color.description}">{car.color.name}</td>
    <td>{car.year}</td>
    <td>{car.createTime}</td>
</tr>
