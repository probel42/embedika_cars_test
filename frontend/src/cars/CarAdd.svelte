<script lang="ts">
    import DataLoader from '../data/DataLoader';
    import type {Car, CarInput} from '../data/Types';
    import {mutation} from 'svelte-apollo';
    import {ADD_CAR} from '../data/Queries';
    import {closeModal, Modals} from 'svelte-modals';

    export let params = null;

    let car: CarInput = {
        regPlate: '',
        brand: '',
        model: '',
        colorCode: null,
        year: null
    };

    const createOrUpdateContract = mutation(ADD_CAR);
    const backToList = () => window.location.href = '';
    const saveCar = () => {
        const opts = {variables: {car: car}};
        createOrUpdateContract(opts).then(backToList).catch((message) => alert(message));
    }
</script>

<style>
    .backdrop {
        position: fixed;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        background: rgba(0, 0, 0, 0.50)
    }

    #carInputDataFields tr td {
        padding-left: 10px;
        padding-right: 10px;
    }
</style>

<Modals>
    <div slot="backdrop" class="backdrop" on:click={closeModal}></div>
</Modals>
<div id="carInputDataFields">
    <table>
        <tr>
            <td>Номер автомобиля</td>
            <td colspan="2"><input type="text" bind:value={car.regPlate}/></td>
        </tr>
        <tr>
            <td>Марка</td>
            <td><input type="text" bind:value={car.brand}/></td>
            <td>Модель</td>
            <td><input type="text" bind:value={car.model}/></td>
        </tr>
        <tr>
            <td>Цвет</td>
            <td><input type="text" bind:value={car.colorCode}/></td>
            <td>Год выпуска</td>
            <td><input type="number" min="1900" max="2099" step="1" bind:value={car.year}></td>
        </tr>
    </table>
</div>
{#if car}
    <button on:click={saveCar}>Сохранить</button>
    <button on:click={backToList}>К списку автомобилей</button>
{/if}
