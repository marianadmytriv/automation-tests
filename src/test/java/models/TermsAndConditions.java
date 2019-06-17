package models;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class TermsAndConditions {
    private boolean termsAndCondition;
    private boolean privacyPolicy;
    private boolean storePersonalData;
}
