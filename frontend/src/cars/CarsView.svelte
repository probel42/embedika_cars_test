<script lang="ts">
    import CarsViewRow from './CarsViewRow.svelte';
    import Paginator from '../Paginator.svelte';
    import Error from '../Error.svelte';
    import DataLoader from '../data/DataLoader';

    const title = 'Список автомобилей';
    let carsNumberPromise = DataLoader.getCarsNumber();
    const perPage = 15;
    let currentPage = 1;
    $: carsPromise = DataLoader.getCars(currentPage, perPage);

    const addCar = () => window.location.href = '#/add'
    const setPage = (pageNumber) => currentPage = pageNumber;
</script>

<style>
    h3 {
        text-align: center;
        margin-top: 0;
    }

    #cars {
        display: grid
    }

    #addCarButton {
        margin-top: 0.5em;
    }

    table {
        border: 2px solid #5a5a5a;
        cursor: default;
    }

    table, th, td {
        border-collapse: collapse;
    }

    table tr th {
        text-align: center;
        background-color: rgb(177, 177, 177);
        border: 1px solid #5a5a5a;
        padding-left: 5px;
        padding-right: 5px;
    }

    td.emptyRow {
        column-span: 5;
        text-align: center;
    }
</style>

{#await carsPromise}
    Loading...
{:then data}
    <h3>{title}</h3>
    <div id="cars">
        {#await carsNumberPromise then data}
            <Paginator {setPage} {perPage} itemsNumber="{data.carsNumber}"/>
        {/await}
        <table>
            <tr>
                <th>Номер</th>
                <th>Марка</th>
                <th>Модель</th>
                <th>Цвет</th>
                <th>Год выпуска</th>
                <th>Дата и время добавления</th>
            </tr>
            {#if data.cars.length === 0}
                <tr>
                    <td class="emptyRow" colspan="6">Автомобили не найдены</td>
                </tr>
            {:else}
                {#each data.cars as car}
                    <CarsViewRow {car}/>
                {/each}
            {/if}
        </table>
    </div>
    <div id="carButtons">
        <button id="addCarButton" on:click={addCar}>Добавить автомобиль</button>
    </div>
{:catch errorMessage}
    <Error {errorMessage}/>
{/await}
