import {IUserRepository} from "./IUserRepository";
import {IUser} from "./IUser";
import * as angular from "angular";

declare const BASE_SERVER_URL: string;

export class HttpUserRepository implements IUserRepository {

    constructor(private $http: angular.IHttpService) {

    }

    findAll(): angular.IPromise<IUser[]> {
        return this.$http.get(BASE_SERVER_URL + "/users")
            .then(result => result.data);
    }

    save(user: IUser): angular.IPromise<void> {
        return this.$http.post( BASE_SERVER_URL + "/users", user)
            .then(() => undefined);
    }

    update(user: IUser): angular.IPromise<void> {
        return this.$http.put( BASE_SERVER_URL + "/users/" + user.id, user)
            .then(() => undefined);
    }
}