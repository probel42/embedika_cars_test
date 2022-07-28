<script lang="ts">
    export let setPage: (pageNumber: number) => void;
    export let perPage;
    export let itemsNumber;

    $: pagesNumber = Math.ceil(itemsNumber / perPage);

    let currentPage = 1;

    const incrementPage = () => {
        currentPage++;
        setPage(currentPage);
    }

    const decrementPage = () => {
        currentPage--;
        setPage(currentPage);
    }
</script>

<style>
    #carsPaginator {
        display: inline-flex;
        margin-left: auto;
    }

    #carsPaginator div {
        margin: 0.4em;
    }
</style>

<div id="carsPaginator">
    <div>Страница:</div>
    <button on:click={decrementPage} disabled="{currentPage === 1}">«</button>
    <div>{currentPage}</div>
    <button on:click={incrementPage} disabled="{currentPage >= pagesNumber}">»</button>
</div>
