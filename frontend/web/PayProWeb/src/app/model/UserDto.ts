import {OrganisationDto} from './OrganisationDto';
import {DepartementDto} from './DepartementDto';

export interface UserDto {
	id: string;
	createdAt: string;
	department: DepartementDto;
	organisation: OrganisationDto;
	displayName: string;
	name: string;
	email: string;
	password: string;
	rule: string;
	cni: string;
}

