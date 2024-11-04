import {UserDto} from './UserDto';
import {OrganisationDto} from './OrganisationDto';

export interface FileDto {
	id: string;
	createdAt: string;
	name: string;
	dateFile: string;
	user: UserDto;
	organisation: OrganisationDto;
	link: string;
}
