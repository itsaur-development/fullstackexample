import {IUser} from "./IUser";

export interface IUserRepository {
    findAll(): angular.IPromise<IUser[]>;
    save(user: IUser): angular.IPromise<void>;
    update(user: IUser): angular.IPromise<void>;
}