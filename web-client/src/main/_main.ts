import {ID, MAIN_STATE} from "./_constants";
import * as angular from "angular";

angular.module(ID, [

]).config(($stateProvider: angular.ui.IStateProvider) => {
    $stateProvider.state(MAIN_STATE, {
        url: "/main",
        templateUrl: "main/main.tpl.html"
    });
});