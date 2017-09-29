import {ID} from "./_constants";
import * as angular from "angular";
import {UserController} from "./UserController";
import {MAIN_STATE} from "../main";
import {ID as mainID} from "../main";
import {HttpUserRepository} from "./HttpUserRepository";

angular.module(ID, [
    "ui.router",
    'md.data.table',
    mainID
])
    .service("$$userRepository", HttpUserRepository)
    .config(($stateProvider: angular.ui.IStateProvider) => {
    $stateProvider.state(MAIN_STATE + ".users", {
        url: "/users",
        templateUrl: "user/user.tpl.html",
        controller: UserController,
        controllerAs: "$$userController"
    });
});