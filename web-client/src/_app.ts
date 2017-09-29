import * as angular from "angular";
import {ID} from "./_constants";
import {ID as userID} from "./user"

angular.module(ID, [
    "ui.router",
    "ngMaterial",
    userID
])
    .config(($urlRouterProvider: angular.ui.IUrlRouterProvider) => {
        $urlRouterProvider.when("", "/main/users");
    });