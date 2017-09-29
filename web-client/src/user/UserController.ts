import {IUserRepository} from "./IUserRepository";
import {IUser} from "./IUser";
import {CreateUserController} from "./create-user/CreateUserController";
import {EditUserController} from "./edit-user/EditUserController";
import * as angular from "angular";

export class UserController {

    users: IUser[];

    constructor(private $$userRepository: IUserRepository, private $mdDialog: angular.material.IDialogService) {
        this.reload();
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