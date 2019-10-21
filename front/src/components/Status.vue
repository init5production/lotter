<template>
  <div class="container">
    <div class="mb-30">
      <table>
        <tr>
          <td>Status:</td>
          <td>{{status}}</td>
        </tr>
        <tr v-if="!isError">
          <td>Archive last draw:</td>
          <td>{{dateOfLastDraw}}</td>
        </tr>
        <tr v-if="isError">
          <td>Error:</td>
          <td>{{errorMsg}}</td>
        </tr>
      </table>
    </div>
    <div class="buttons are-small">
      <button class="button is-info" :class="{ 'is-loading':  processes.checkingStatus }" @click="checkStatus">STATUS</button>
      <button class="button is-info" :class="{ 'is-loading':  processes.updatingArchive }" @click="updateArchive">UPDATE ARCHIVE</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import * as JUGGLER from '../juggler_config';

export default {
  name: 'Status',
  data () {
    return {
      status: "",
      dateOfLastDraw: "",
      isError: false,
      errorMsg: "",
      processes: {
        checkingStatus: false,
        updatingArchive: false,
      }
    }
  },
  created: function () {
    this.checkStatus();
  },
  methods: {
    checkStatus() {
      var component = this;
      component.processes.checkingStatus = true;

      axios
        .get(JUGGLER.ROOT_URL + JUGGLER.ENDPOINT_HEALTH_CHECK)
        .then(function(response) {
          if (response.data.status == 'OK') {
            component.isError = false;
            component.status = response.data.status;
            component.dateOfLastDraw = response.data.dateOfLastDraw;
          } else {
            component.isError = true;
            component.status = response.data.status;
            component.errorMsg = response.data.error;
          }

          component.processes.checkingStatus = false;
        })
        .catch(function (error) {
          component.processes.checkingStatus = false;

          component.isError = true;
          component.errorMsg = typeof error.response == 'undefined' ? "Could not get response from server." : "Invalid server response. HTTP code: " + error.response.status;
        });
    },
    updateArchive() {
      var component = this;
      component.processes.updatingArchive = true;

      axios
        .post(JUGGLER.ROOT_URL + JUGGLER.ENDPOINT_UPDATE_ARCHIVE)
        .then(function(response) {
          component.processes.updatingArchive = false;
          component.checkStatus();
        })
        .catch(function (error) {
          component.processes.updatingArchive = false;
          console.error("Error while updating archive! HTTP code: " + error.response.status);
        });
    }
  }
}
</script>

<style scoped>
  td {
    padding: 0 7px;
  }
</style>
