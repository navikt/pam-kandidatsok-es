package no.nav.arbeid.cv.es.util;

import no.nav.security.oidc.OIDCConstants;
import no.nav.security.oidc.context.OIDCValidationContext;
import no.nav.security.oidc.filter.OIDCRequestContextHolder;

public class FnrExtractor {

    public static String extract(OIDCRequestContextHolder ctxHolder) {
        OIDCValidationContext context = (OIDCValidationContext) ctxHolder
            .getRequestAttribute(OIDCConstants.OIDC_VALIDATION_CONTEXT);
        return context.getClaims("selvbetjening").getClaimSet().getSubject();
    }

}