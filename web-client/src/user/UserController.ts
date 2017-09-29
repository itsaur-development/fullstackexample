import {IUserRepository} from "./IUserRepository";
import {IUser} from "./IUser";
import {CreateUserController} from "./create-user/CreateUserController";
import {EditUserController} from "./edit-user/EditUserController";
import * as angular from "angular";
import * as webstomp from "webstomp-client";

declare const BASE_SERVER_URL: string;

export class UserController {

    users: IUser[];
    client: webstomp.Client;
    subscription: webstomp.Subscription;

    constructor(private $$userRepository: IUserRepository, private $mdDialog: angular.material.IDialogService, private $scope: angular.IScope) {
        this.reload();
        this.initWebsocket();
    }

    initWebsocket(): void {
        this.client = webstomp.client(BASE_SERVER_URL.replace("http", "ws") + "/events");
        this.client.connect({
            login: undefined,
            passcode: undefined
        }, () => {
            this.subscription = this.client.subscribe("/topic/events", () => this.reload());
        });

        this.$scope.$on("$destroy", () => {
            this.client.unsubscribe(this.subscription.id);
            this.client.disconnect(() => {})
        });
    }

    reload(): void {
        this.$$userRepository.findAll().then(users => this.users = users);
    }

    createUser(): void {
        this.$mdDialog.show({
            templateUrl: "user/create-user/create-user.tpl.html",
            controller: CreateUserController,
            controllerAs: "$$createUserController"
        });
    }

    editUser(user: IUser): void {
        this.$mdDialog.show({
            templateUrl: "user/edit-user/edit-user.tpl.html",
            controller: EditUserController,
            controllerAs: "$$editUserController",
            bindToController: true,
            locals: {
                user: angular.copy(user)
            }
        });
    }
}