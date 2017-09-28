import {IUser} from "./IUser";

export interface IUserRepository {
    findAll(): angular.IPromise<IUser[]>;
}