import { Component } from '@angular/core';
import { lucideChevronDown } from '@ng-icons/lucide';
import {HlmIconComponent} from '../../../ui-icon-helm/src';
import {provideIcons} from '@ng-icons/core';

@Component({
	selector: 'hlm-select-scroll-down',
	standalone: true,
	imports: [HlmIconComponent],
	providers: [provideIcons({ lucideChevronDown })],
	host: {
		class: 'flex cursor-default items-center justify-center py-1',
	},
	template: `
		<hlm-icon class="ml-2 h-4 w-4" name="lucideChevronDown" />
	`,
})
export class HlmSelectScrollDownComponent {}
