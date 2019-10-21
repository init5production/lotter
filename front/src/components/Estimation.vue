<template>
  <div class="container">
    <div class="columns">
      <div class="column">
        <div>
          <p>Choose one or more strategy to use:</p>
          <ul class="listing">
            <li v-for="(strategy, index) in strategies">
              <input :id="strategy.name + index" type="checkbox" :value="strategy.name" v-model="selectedStrategies">
              <label class="strategy-label" :for="strategy.name + index">{{ strategy.name }}</label>
            </li>
          </ul>
        </div>
        <div class="buttons are-small">
          <button class="button is-info" :class="{ 'is-loading':  processes.estimating }" @click="estimate">ESTIMATE</button>
        </div>
      </div>
      <div class="column" v-if="hasToShowEstimated">
        <p class="mb-30">Estimated results:</p>
        <table>
          <tr v-for="item in estimated">
            <td>{{ item.strategy }}</td>
            <td class="pl-20"><span class="l-number" v-for="number in item.numbers">{{ number }}</span></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import * as JUGGLER from '../juggler_config';

export default {
  name: 'Estimation',
  data () {
    return {
      strategies: [],
      selectedStrategies: [],
      estimated: [],
      processes: {
        estimating: false
      }
    }
  },
  created: function () {
    this.getStrategies();
  },
  computed: {
    hasToShowEstimated() {
      return (typeof this.estimated) == 'object' && this.estimated.length > 0;
    }
  },
  methods: {
    getStrategies() {
      let component = this;
      
      axios
        .get(JUGGLER.ROOT_URL + JUGGLER.ENDPOINT_ESTIMATION_STRATEGIES)
        .then(function(response) {
          component.strategies = response.data.strategies;
        })
        .catch(function (error) {
          console.error("Could not get estimation strategies from server. HTTP code: " + error.response.status);
        });
    },
    estimate() {
      let component = this;
      component.estimated = [];
      component.processes.estimating = true;

      axios
        .post(JUGGLER.ROOT_URL + JUGGLER.ENDPOINT_ESTIMATE, { strategies: component.selectedStrategies })
        .then(function(response) {
          let results = response.data.estimated;

          for (let key in results) {
            component.estimated.push({strategy: key, numbers: results[key]});
          }
          
          component.processes.estimating = false;
        })
        .catch(function (error) {
          component.processes.estimating = false;
          console.error("Could not estimate. HTTP code: " + error.response.status);
        });
    }
  }
}
</script>

<style scoped>
.strategy-label {
  margin-left: 10px;
  cursor: pointer;
}
.listing {
  margin: 30px 5px;
}
.l-number {
    color: #aaa;
    width: 2.2em;
    display: inline-block;
    text-align: right;
}
</style>
