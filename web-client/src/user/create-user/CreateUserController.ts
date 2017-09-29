import {IUserRepository} from "../IUserRepository";
import {IUser} from "../IUser";

export class CreateUserController {

    private user: IUser;

    constructor(private $mdDialog: angular.material.IDialogService,
                private $$userRepository: IUserRepository,
                private $mdToast: angular.material.IToastService) {
        this.user = {};
    }

    close(): void {
        this.$mdDialog.hide();
    }

    save(userForm: angular.IFormController): void {
        if(userForm.$invalid) {
            return;
        }

        this.$$userRepository
            .save(this.user)
            .then(() => this.$mdDialog.hide())
            .catch((error) => {
                this.$mdToast.showSimple("Could not save user!");
            });
    }
}