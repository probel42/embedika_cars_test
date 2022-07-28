<script lang="ts">
    import ApolloClient from 'apollo-boost';
    import {setClient} from 'svelte-apollo';
    import CarsView from './cars/CarsView.svelte';
    import CarAdd from './cars/CarAdd.svelte';
    import Router from 'svelte-spa-router'

    // const env = _env;
    const client = new ApolloClient({uri: 'http://localhost:8080/'});
    setClient(client);

    const routes = {
        '/': CarsView,
        '/add': CarAdd
    }
</script>

<body>
<Router {routes}/>
</body>

<style>
    @media (min-width: 640px) {
    }
</style>
