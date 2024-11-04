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
  gender: string;
  // image: string | undefined;
  dateBirth: string | undefined;
  address: string | undefined;
  phone: string | undefined;
}

