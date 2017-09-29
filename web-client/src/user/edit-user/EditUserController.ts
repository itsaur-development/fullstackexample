import {IUserRepository} from "../IUserRepository";
import {IUser} from "../IUser";

export class EditUserController {

    constructor(private $mdDialog: angular.material.IDialogService,
                private $$userRepository: IUserRepository,
                private $mdToast: angular.material.IToastService,
                private user: IUser) {
    }

    close(): void {
        this.$mdDialog.hide();
    }

    save(userForm: angular.IFormController): void {
        if(userForm.$invalid) {
            return;
        }

        this.$$userRepository
            .update(this.user)
            .then(() => this.$mdDialog.hide())
            .catch((error) => {
                this.$mdToast.showSimple("Could not save user!");
            });
    }
}