<template>
  <div id="app">
    
      <nav id="nav">
        <div class="container">
          <div class="columns is-vcentered">
            <div class="column is-narrow">
              <h2 class="is-size-3 logotype">LO<span class="text-yellow">TT</span>ER</h2>
            </div>
            <div class="column">
              <ul class="menu">
                <li><router-link :to="{ name: 'Status' }">STATUS</router-link></li>
                <li><router-link :to="{ name: 'Estimation' }">ESTIMATION</router-link></li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
  
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
#nav {
   margin: 60px 0;
}
.menu li {
  display: inline-block;
  margin: 0 5px;
}
.menu a {
  padding: 5px;
  font-weight: 700;
  font-size: 0.85em;
  color: #666;
}
.logotype {
  font-weight: 700;
  width: 150px;
}
</style>
