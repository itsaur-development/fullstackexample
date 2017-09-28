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

}