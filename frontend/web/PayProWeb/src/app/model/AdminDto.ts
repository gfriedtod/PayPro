import {AdminRowDto} from './AdminRowDto';

export interface AdminDto {
	id?: string;
	name: string;
	email: string;
	password: string;
  phone? : string;
  address? : string;
	role: RoleDto;
	adminDepartments?: AdminDtoAdminDepartments[];
	adminRows?: AdminRowDto[];
	space: AdminDtoSpace;

}
export interface RoleDto {
	id: string;
	name: string;
}
export interface AdminDtoAdminDepartmentsDepartmentOrganisation {
	id: string;
	name: string;
}
export interface AdminDtoAdminDepartmentsDepartment {
	id: string;
	createdAt: string;
	name: string;
	organisation: AdminDtoAdminDepartmentsDepartmentOrganisation;
}
export interface AdminDtoAdminDepartments {
	id: number;
	createdAt: string;
	department: AdminDtoAdminDepartmentsDepartment;
}
export interface AdminDtoAdminRowsOrganisation {
	id: string;
	name: string;
}
export interface AdminRows {
	id: number;
	organisation: AdminDtoAdminRowsOrganisation;
}
export interface OrganisationDto {
	id: string;
	name: string;
}
export interface AdminDtoSpace {
	id: string;
	organisationDtos: OrganisationDto[];
}
