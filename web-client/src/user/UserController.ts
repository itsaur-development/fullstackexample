import {IUserRepository} from "./IUserRepository";
import {IUser} from "./IUser";

export class UserController {

    users: IUser[];

    constructor(private $$userRepository: IUserRepository) {
        $$userRepository.findAll().then(users => this.users = users);
    }


}