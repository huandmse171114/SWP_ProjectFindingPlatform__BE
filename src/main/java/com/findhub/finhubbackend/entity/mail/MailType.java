package com.findhub.finhubbackend.entity.mail;

public enum MailType {
    CONFRIM_RECEIVE_APPLICATION("ConfirmReceiveApplicationForm.html"),
    APPROVED("ApproveApplicationFormForProject.html"),
    REJECTED("RejectApplicationFormForProject.html"),
    JOIN("Joinrequest.html"),
    INVITE("InviteToTeam.html"),
    ;

    private final String value;
	// private static final Map<String, String> status = new HashMap<>();
	// private static final List<String> model = new ArrayList<>();

	// static {
	// 	// only java 10+
	// 	for (var ps : values()) {

	// 		String e = ps.getValue();
	// 		String name = Utils.capitalize(ps.name());

	// 		status.put(e, name);
	// 		model.add(ps.name());
	// 	}

	// }

    private MailType(String value) {
        this.value = value;
    }

    public String getValue() {
		return this.value;
	}

	// public static String nameOf(String val) {
	// 	return Utils.capitalize(status.get(val));
	// }

    // public static String valOf(String val) {
	// 	val = val.toUpperCase();
	// 	for (var s : values())
	// 		if (s.name().equals(val))
	// 			return s.getValue();
	// 	return null;
	// }

    // public static boolean isExisted(String val) {
	// 	return status.get(val) != null;
	// }

	// public static boolean isExisted(String val) {
	// 	if (Utils.isNum(val))
	// 		return isExisted(Integer.parseInt(val));

	// 	val = val.toUpperCase();
	// 	try {
	// 		valueOf(val);
	// 		return true;
	// 	} catch (Exception e) {
	// 		return false;
	// 	}
	// }

	// public static List<String> getAll() {
	// 	return model;
	// }
}
